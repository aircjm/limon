import sugar from "sugar";

import locals from 'sugar/locales'

export function parseDate(dateStr) {
    let date = sugar.Date.create(dateStr, "zh-CN");
    return date
}




