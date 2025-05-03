ALTER TABLE transaction
ALTER COLUMN transaction_type TYPE VARCHAR
USING transaction_type::text;
