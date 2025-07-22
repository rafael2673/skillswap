-- changeset Rafael:078

CREATE VIEW learner_profile_skill_view AS
SELECT
    p.first_name,
    p.last_name,
    p.bio,
    p.picture_url,
    s.name AS skill_name,
    ps.role_in_skill,
    ps.proficiency_level
FROM
    profile p
        JOIN
    profile_skill ps ON p.profile_id = ps.profile_id
        JOIN
    skill s ON ps.skill_id = s.skill_id
WHERE
    ps.role_in_skill = 'learner';


-- rollback DROP VIEW learner_profile_skill_view

-- changeset Rafael:079
CREATE VIEW teacher_profile_skill_view AS
SELECT
    p.first_name,
    p.last_name,
    p.bio,
    p.picture_url,
    s.name AS skill_name,
    ps.role_in_skill,
    ps.proficiency_level
FROM
    profile p
        JOIN
    profile_skill ps ON p.profile_id = ps.profile_id
        JOIN
    skill s ON ps.skill_id = s.skill_id
WHERE
    ps.role_in_skill = 'teacher';


-- rollback DROP VIEW teacher_profile_skill_view