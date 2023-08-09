CREATE TABLE battle_history
(
    id              UUID PRIMARY KEY NOT NULL DEFAULT uuid_generate_v4(),
    hero_one_id     UUID     		NOT NULL,
    hero_two_id     UUID     		NOT NULL,
    hero_win_id     UUID     		NOT NULL,
    CONSTRAINT FK_hero FOREIGN KEY (hero_win_id) REFERENCES hero(id)
);