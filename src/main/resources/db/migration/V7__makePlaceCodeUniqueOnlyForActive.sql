
ALTER TABLE place
    DROP CONSTRAINT IF EXISTS uc_place_code;


CREATE UNIQUE INDEX uk_place_code_active
ON place (code)
WHERE active = true;