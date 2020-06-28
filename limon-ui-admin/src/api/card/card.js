import request from '@/utils/request'

// 查询列表
export function listData(query) {
  return request({
    url: '/api/card/list',
    method: 'post',
    data: query
  })
}

// 设置anki
export function setAnki(setAnkiRequest) {
  return request({
    url: '/api/card/setAnki',
    method: 'post',
    data: setAnkiRequest
  })
}


// 查询列表
export function exportCard(query) {
  return request({
    url: '/api/card/exportCard',
    method: 'post',
    data: query
  })
}
