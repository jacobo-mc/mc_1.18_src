package com.mojang.realmsclient.exception;

import com.mojang.realmsclient.client.RealmsError;
import net.minecraft.client.resources.language.I18n;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RealmsServiceException extends Exception {
   public final int httpResultCode;
   public final String httpResponseContent;
   public final int errorCode;
   public final String errorMsg;

   public RealmsServiceException(int p_87783_, String p_87784_, RealmsError p_87785_) {
      super(p_87784_);
      this.httpResultCode = p_87783_;
      this.httpResponseContent = p_87784_;
      this.errorCode = p_87785_.getErrorCode();
      this.errorMsg = p_87785_.getErrorMessage();
   }

   public RealmsServiceException(int p_87778_, String p_87779_, int p_87780_, String p_87781_) {
      super(p_87779_);
      this.httpResultCode = p_87778_;
      this.httpResponseContent = p_87779_;
      this.errorCode = p_87780_;
      this.errorMsg = p_87781_;
   }

   public String toString() {
      if (this.errorCode == -1) {
         return "Realms (" + this.httpResultCode + ") " + this.httpResponseContent;
      } else {
         String s = "mco.errorMessage." + this.errorCode;
         String s1 = I18n.get(s);
         return (s1.equals(s) ? this.errorMsg : s1) + " - " + this.errorCode;
      }
   }
}