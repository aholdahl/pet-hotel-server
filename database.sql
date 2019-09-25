--database name: pet_hotel

CREATE TABLE "owners" (
	"id" SERIAL PRIMARY KEY,
	"owner_name" TEXT
);

INSERT INTO "owners" ("owner_name") VALUES ('David');

CREATE TABLE "pets" (
	"id" SERIAL PRIMARY KEY,
	"owner_id" INT REFERENCES "owners" NOT NULL,
	"pet_name" TEXT NOT NULL,
	"breed" TEXT,
	"color" TEXT,
	"is_checked_in" BOOLEAN DEFAULT false,
	"checked_in_date" DATE
);

INSERT INTO "pets" ("owner_id","pet_name") VALUES (1,'Anya'),(1,'Tessa');