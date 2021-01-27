INSERT INTO manufacturers (mft_id, mft_name)
    VALUES
    (nextval('public.mft_id_seq'), 'MTK TOYS'),
    (nextval('public.mft_id_seq'), 'FigUniverse'),
    (nextval('public.mft_id_seq'), 'Action Industries Ltda.'),
    (nextval('public.mft_id_seq'), 'TL Factories'),
    (nextval('public.mft_id_seq'), 'GWT'),
    (nextval('public.mft_id_seq'), 'Mini Co.');

INSERT INTO categories (ctg_id, ctg_name)
    VALUES
    (nextval('public.ctg_id_seq'), 'Marvel'),
    (nextval('public.ctg_id_seq'), 'DC Comics'),
    (nextval('public.ctg_id_seq'), 'Seriados'),
    (nextval('public.ctg_id_seq'), 'Animes e Mangás'),
    (nextval('public.ctg_id_seq'), 'Games'),
    (nextval('public.ctg_id_seq'), 'Quadrinhos'),
    (nextval('public.ctg_id_seq'), 'Cinema & TV'),
    (nextval('public.ctg_id_seq'), 'Diversos');

INSERT INTO materials (mtr_id, mtr_name)
    VALUES
    (nextval('public.mtr_id_seq'), 'PVC'),
    (nextval('public.mtr_id_seq'), 'ABS'),
    (nextval('public.mtr_id_seq'), 'Resina'),
    (nextval('public.mtr_id_seq'), 'Vinil'),
    (nextval('public.mtr_id_seq'), 'Coldcast'),
    (nextval('public.mtr_id_seq'), 'Polystone');
