import axios from 'axios'

// 保存
export function saveTag (data) {
  return axios({
    url: '/api/tag/save',
    method: 'post',
    data: data
  })
}

// 列表搜索
export function getTagList (data) {
  return axios({
    url: '/api/tag/list',
    method: 'post',
    data: data
  })
}
