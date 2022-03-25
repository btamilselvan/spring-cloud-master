package com.success.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CodesPK implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(name = "co_type")
  private String coType;

  @Column(name = "co_subtype")
  private String coSubtype;
}
