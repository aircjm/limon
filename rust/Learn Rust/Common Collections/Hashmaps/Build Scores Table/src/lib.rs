use std::collections::HashMap;

// A structure to store team name and its goal details.
#[derive(Debug)]
pub struct Team {
    pub name: String,
    pub goals_scored: u8,
    pub goals_conceded: u8,
}

pub fn build_scores_table(results: String) -> HashMap<String, Team> {
    // The name of the team is the key and its associated struct is the value.
    let mut scores: HashMap<String, Team> = HashMap::new();

    for r in results.lines() {
        let v: Vec<&str> = r.split(',').collect();
        let team_1_name = v[0].to_string();
        let team_1_score: u8 = v[2].parse().unwrap();
        let team_2_name = v[1].to_string();
        let team_2_score: u8 = v[3].parse().unwrap();
        /* TODO: Populate the scores table with details extracted from the
        current line. Keep in mind that goals scored by team_1
        will be number of goals conceded from team_2, and similarly
        goals scored by team_2 will be the number of goals conceded by
        team_1. */
    }
    scores
}
