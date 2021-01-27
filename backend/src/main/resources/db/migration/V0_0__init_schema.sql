-- CREATE TABLES

CREATE TABLE manufacturers (
    mft_id BIGSERIAL NOT NULL,
    mft_name VARCHAR(50) NOT NULL
);

CREATE TABLE materials (
    mtr_id BIGSERIAL NOT NULL,
    mtr_name VARCHAR(50) NOT NULL
);

CREATE TABLE categories (
    ctg_id BIGSERIAL NOT NULL,
    ctg_name VARCHAR(50) NOT NULL
);

CREATE TABLE action_figures (
    acf_id BIGSERIAL NOT NULL,
    acf_name VARCHAR(100) NOT NULL,
    acf_image_url VARCHAR(1000),
    acf_price REAL NOT NULL,
    acf_short_description VARCHAR(50) NOT NULL,
    acf_long_description VARCHAR(120),
    acf_approximate_height INTEGER,
    acf_recommended_age INTEGER,
    acf_male BIT,
    acf_full_angle_image BIT,
    acf_mft_id BIGINT NOT NULL
);

CREATE TABLE action_figures_materials (
	afm_acf_id BIGINT NOT NULL,
	afm_mtr_id BIGINT NOT NULL
);

CREATE TABLE action_figures_categories (
	afc_acf_id BIGINT NOT NULL,
	afc_ctg_id BIGINT NOT NULL
);

CREATE TABLE stock (
	stc_id BIGSERIAL NOT NULL,
	stc_acf_id BIGINT NOT NULL,
	stc_amount INT NOT NULL
);

CREATE TABLE stock_history (
	sth_id BIGSERIAL NOT NULL,
	sth_stc_id BIGINT NOT NULL,
	sth_amount INT NOT NULL,
	sth_date TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- PRIMARY KEYS

ALTER TABLE manufacturers
    ADD CONSTRAINT mft_pk PRIMARY KEY (mft_id);

ALTER TABLE materials
    ADD CONSTRAINT mtr_pk PRIMARY KEY (mtr_id);

ALTER TABLE categories
    ADD CONSTRAINT ctg_pk PRIMARY KEY (ctg_id);

ALTER TABLE action_figures
    ADD CONSTRAINT acf_pk PRIMARY KEY (acf_id);

ALTER TABLE action_figures_materials
    ADD CONSTRAINT afm_pk PRIMARY KEY (afm_acf_id, afm_mtr_id);

ALTER TABLE action_figures_categories
    ADD CONSTRAINT afc_pk PRIMARY KEY (afc_acf_id, afc_ctg_id);

ALTER TABLE stock
    ADD CONSTRAINT stc_pk PRIMARY KEY (stc_id);

ALTER TABLE stock_history
    ADD CONSTRAINT sth_pk PRIMARY KEY (sth_id);


-- FOREIGN KEYS

ALTER TABLE action_figures
    ADD CONSTRAINT acf_mft_fk FOREIGN KEY (acf_mft_id) REFERENCES manufacturers (mft_id);

ALTER TABLE action_figures_materials
    ADD CONSTRAINT afm_acf_fk FOREIGN KEY (afm_acf_id) REFERENCES action_figures (acf_id);

ALTER TABLE action_figures_materials
    ADD CONSTRAINT afm_mtr_fk FOREIGN KEY (afm_mtr_id) REFERENCES materials (mtr_id);

ALTER TABLE action_figures_categories
    ADD CONSTRAINT afc_acf_fk FOREIGN KEY (afc_acf_id) REFERENCES action_figures (acf_id);

ALTER TABLE action_figures_categories
    ADD CONSTRAINT afc_ctg_fk FOREIGN KEY (afc_ctg_id) REFERENCES categories (ctg_id);

ALTER TABLE stock
    ADD CONSTRAINT stc_acf_fk FOREIGN KEY (stc_acf_id) REFERENCES action_figures (acf_id);

ALTER TABLE stock_history
    ADD CONSTRAINT sth_stc_fk FOREIGN KEY (sth_stc_id) REFERENCES stock (stc_id);