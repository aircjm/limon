import request from '@/utils/request'


// 刷新所有的卡片
export function refreshAllCard() {
  return request({
    url: '/system/job/refreshAllCard',
    method: 'get'
  })
}
