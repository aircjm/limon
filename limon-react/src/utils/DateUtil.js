import sugar from "sugar";

export function parseDate(dateStr) {
    let date = sugar.Date.create(dateStr, "zh-CN");
    return date
}




