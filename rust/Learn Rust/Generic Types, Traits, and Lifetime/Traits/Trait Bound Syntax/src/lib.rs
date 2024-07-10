/* Something goes here */

pub struct ReportCard/* Something goes here */ {
    pub grade: f32,
    pub student_name: String,
    pub student_age: u8,
}

impl/* Something goes here */ ReportCard/* Something goes here */ {
    pub fn print(&self) -> String {
        format!("{} ({}) - achieved a grade of {}",
                &self.student_name, &self.student_age, &self.grade)
    }
}