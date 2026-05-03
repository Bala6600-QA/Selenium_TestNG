CREATE SCHEMA IF NOT EXISTS healenium;
GRANT USAGE ON SCHEMA healenium TO saucedemo_healenium_user;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA healenium TO saucedemo_healenium_user;
ALTER ROLE saucedemo_healenium_user SET search_path TO healenium;