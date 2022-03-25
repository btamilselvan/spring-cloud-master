package com.success.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "codes")
public class Codes implements Serializable {
  private static final long serialVersionUID = 1L;

  @EmbeddedId private CodesPK id;

  @Column(name = "co_value")
  private String coValue;
}
