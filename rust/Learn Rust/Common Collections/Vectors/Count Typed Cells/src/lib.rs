pub enum SpreadsheetCell {
    Int(i32),
    Float(f64),
    Text(String),
}

#[derive(Debug)]
pub struct CellsCounter {
    pub ints: usize,
    pub floats: usize,
    pub texts: usize
}

pub fn count_cells(vec: &Vec<SpreadsheetCell>) -> CellsCounter {
    let mut cc = CellsCounter {ints: 0, floats: 0, texts: 0};
    for v in /* TODO */ {
        match v {
            /* Add match cases */
        }
    }
    cc
}