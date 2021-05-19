import {date} from 'quasar'


export function convertMinToMs(min) {
  return min * 60 * 1000;
}


export function getFormatDateTime(dateTime) {
  return date.formatDate(dateTime, 'YYYY-MM-DD HH:mm:ss')
}

export function getTimestamp(dateTime) {
  return date.formatDate(dateTime, 'x')
}


