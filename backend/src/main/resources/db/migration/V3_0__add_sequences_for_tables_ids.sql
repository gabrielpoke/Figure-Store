CREATE SEQUENCE public.mft_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE public.mtr_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE public.ctg_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE public.acf_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE public.stc_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE public.sth_id_seq
    START WITH 100
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.mft_id_seq OWNED BY public.manufacturers.mft_id;

ALTER SEQUENCE public.mtr_id_seq OWNED BY public.materials.mtr_id;

ALTER SEQUENCE public.ctg_id_seq OWNED BY public.categories.ctg_id;

ALTER SEQUENCE public.acf_id_seq OWNED BY public.action_figures.acf_id;

ALTER SEQUENCE public.stc_id_seq OWNED BY public.stock.stc_id;

ALTER SEQUENCE public.sth_id_seq OWNED BY public.stock_history.sth_id;


ALTER TABLE ONLY public.manufacturers ALTER COLUMN mft_id SET DEFAULT nextval('public.mft_id_seq'::regclass);

ALTER TABLE ONLY public.materials ALTER COLUMN mtr_id SET DEFAULT nextval('public.mtr_id_seq'::regclass);

ALTER TABLE ONLY public.categories ALTER COLUMN ctg_id SET DEFAULT nextval('public.ctg_id_seq'::regclass);

ALTER TABLE ONLY public.action_figures ALTER COLUMN acf_id SET DEFAULT nextval('public.acf_id_seq'::regclass);

ALTER TABLE ONLY public.stock ALTER COLUMN stc_id SET DEFAULT nextval('public.stc_id_seq'::regclass);

ALTER TABLE ONLY public.stock_history ALTER COLUMN sth_id SET DEFAULT nextval('public.sth_id_seq'::regclass);
