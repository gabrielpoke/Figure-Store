package com.figure.store.domain.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter

@Entity
@Table(name = "action_figures")
public class ActionFigure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acf_id")
	private Long id;

	@Basic
	@Column(name = "acf_name")
	private String name;

	@Basic
	@Column(name = "acf_image_url")
	private String imageUrl;

	@Basic
	@Column(name = "acf_price")
	private Double price;

	@Basic
	@Column(name = "acf_short_description")
	private String shortDescription;

	@Basic
	@Column(name = "acf_long_description")
	private String longDescription;

	@Basic
	@Column(name = "acf_approximate_height")
	private Integer approximateHeight;

	@Basic
	@Column(name = "acf_recommended_age")
	private Integer recommendedAge;

	@OneToMany
	@JoinTable(name = "action_figures_categories",
			joinColumns = @JoinColumn(name = "afc_acf_id"),
			inverseJoinColumns = @JoinColumn(name = "afc_ctg_id"))
	private Collection<Category> categories;

	@OneToOne
	@JoinColumn(
			name = "acf_mft_id",
			referencedColumnName = "mft_id")
	private Manufacturer manufacturer;

	@OneToMany
	@JoinTable(name = "action_figures_materials",
			joinColumns = @JoinColumn(name = "afm_acf_id"),
			inverseJoinColumns = @JoinColumn(name = "afm_mtr_id"))
	private Collection<Material> materials;
}
