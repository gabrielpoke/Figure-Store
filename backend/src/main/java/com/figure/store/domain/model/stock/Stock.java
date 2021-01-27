package com.figure.store.domain.model.stock;

import com.figure.store.domain.model.product.ActionFigure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "stock")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stc_id")
	private Long id;

	@Column(name = "stc_amount")
	private Integer amount;

	@OneToOne(orphanRemoval = true, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(
			name = "stc_acf_id",
			referencedColumnName = "acf_id")
	private ActionFigure actionFigure;

}
