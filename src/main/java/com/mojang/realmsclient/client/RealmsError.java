package com.mojang.realmsclient.client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.realmsclient.util.JsonUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@OnlyIn(Dist.CLIENT)
public class RealmsError {
   private static final Logger LOGGER = LogManager.getLogger();
   private final String errorMessage;
   private final int errorCode;

   private RealmsError(String p_87300_, int p_87301_) {
      this.errorMessage = p_87300_;
      this.errorCode = p_87301_;
   }

   public static RealmsError create(String p_87304_) {
      try {
         JsonParser jsonparser = new JsonParser();
         JsonObject jsonobject = jsonparser.parse(p_87304_).getAsJsonObject();
         String s = JsonUtils.getStringOr("errorMsg", jsonobject, "");
         int i = JsonUtils.getIntOr("errorCode", jsonobject, -1);
         return new RealmsError(s, i);
      } catch (Exception exception) {
         LOGGER.error("Could not parse RealmsError: {}", (Object)exception.getMessage());
         LOGGER.error("The error was: {}", (Object)p_87304_);
         return new RealmsError("Failed to parse response from server", -1);
      }
   }

   public String getErrorMessage() {
      return this.errorMessage;
   }

   public int getErrorCode() {
      return this.errorCode;
   }
}