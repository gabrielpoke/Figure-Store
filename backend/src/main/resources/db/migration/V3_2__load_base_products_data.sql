INSERT INTO action_figures
    (acf_id, acf_name, acf_image_url, acf_price, acf_short_description, acf_long_description, acf_approximate_height, acf_recommended_age, acf_mft_id)
    VALUES
    (nextval('public.acf_id_seq'), 'Gandalf - Lord Of The Rings', 'https://www.fantoy.com.br/media/catalog/product/cache/5070b15b05522f191912dd31c57262ab/g/a/gandalf4.jpg', 179.0, 'Estátua do personagem Gandalf do filme O Senhor Dos Anéis', 'Características: \n\n- Criação: Iron Studios \n- Fabricado em plástico (PVC) \n- Pintado a mão \n- Inclui base', 18, 15, 105),
    (nextval('public.acf_id_seq'), 'Frodo - Lord Of The Rings', 'https://www.fantoy.com.br/media/catalog/product/cache/5070b15b05522f191912dd31c57262ab/f/r/frodo_1.jpg', 159.0, 'Estátua do personagem Frodo do filme O Senhor Dos Anéis', 'Características: \n\n- Criação: Iron Studios \n- Fabricado em plástico (PVC) \n- Pintado a mão \n- Inclui base', 11, 15, 105),
    (nextval('public.acf_id_seq'), 'Stan Lee', 'https://www.fantoy.com.br/media/catalog/product/cache/5070b15b05522f191912dd31c57262ab/s/t/stanlee_1_.jpg', 189.0, 'Estátua do quadrinista Stan Lee', 'Características: \n\n- Criação: Iron Studios \n- Fabricado em plástico (PVC) \n- Pintado a mão \n- Inclui base', 9, 15, 105);

INSERT INTO action_figures_materials (afm_acf_id, afm_mtr_id)
    VALUES
    (100, 100),
    (101, 100),
    (102, 100);

INSERT INTO action_figures_categories (afc_acf_id, afc_ctg_id)
    VALUES
    (100, 106),
    (100, 107),
    (101, 106),
    (101, 107),
    (102, 105),
    (102, 107);

INSERT INTO stock (stc_id, stc_acf_id, stc_amount)
    VALUES
    (nextval('public.stc_id_seq'), 100, 30),
    (nextval('public.stc_id_seq'), 101, 50),
    (nextval('public.stc_id_seq'), 102, 70);

INSERT INTO stock_history (sth_id, sth_stc_id, sth_amount, sth_date)
    VALUES
    (nextval('public.sth_id_seq'), 100, 30, current_timestamp),
    (nextval('public.sth_id_seq'), 101, 50, current_timestamp),
    (nextval('public.sth_id_seq'), 102, 70, current_timestamp);
