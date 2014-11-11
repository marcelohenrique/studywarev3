ALTER TABLE Usuario ADD COLUMN ativo BOOLEAN DEFAULT FALSE NOT NULL;

UPDATE Usuario SET ativo = TRUE WHERE email = 'mhguara@gmail.com';