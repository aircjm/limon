import request from '@/utils/request'

// 查询列表
export function listData(query) {
  return request({
    url: '/api/card/list',
    method: 'post',
    data: query
  })
}
