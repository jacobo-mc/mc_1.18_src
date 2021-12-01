package net.minecraft.core;

import javax.annotation.Nullable;

public interface IdMap<T> extends Iterable<T> {
   int DEFAULT = -1;

   int getId(T p_122652_);

   @Nullable
   T byId(int p_122651_);

   int size();
}