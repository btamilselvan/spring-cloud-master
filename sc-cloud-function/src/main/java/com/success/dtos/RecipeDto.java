package com.success.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class RecipeDto {
  @ToString.Include private String recipeId;

  @ToString.Include private String recipeNum;

  @ToString.Include private String title;

  private String additionalTitle;

  private String description;
}
