CREATE TABLE public.authorities (
  username    CHARACTER VARYING(50) NOT NULL,
  authorities CHARACTER VARYING(50) NOT NULL,
  FOREIGN KEY (username) REFERENCES public.users (username)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);