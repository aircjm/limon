import request from '@/utils/request'


// 刷新所有的卡片
export function refreshAllCard(data) {
  return request({
    url: '/system/job/refreshAllCard',
    method: 'post',
    data: data
  })
}
