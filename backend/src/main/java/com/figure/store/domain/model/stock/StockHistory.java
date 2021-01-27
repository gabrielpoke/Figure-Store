package com.figure.store.domain.model.stock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "stock_history")
public class StockHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sth_id")
	private Long id;

	@Basic
	@Column(name = "sth_amount")
	private Integer amount;

	@CreatedDate
	@Column(name = "sth_date")
	private LocalDateTime date;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sth_stc_id", referencedColumnName = "stc_id")
	private Stock stock;

}
