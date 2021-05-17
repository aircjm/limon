import client from "src/utils/axios";

// 保存
export function saveTag (data) {
  return client({
    url: '/api/tag/save',
    method: 'post',
    data: data
  })
}

// 列表搜索
export function getTagList (data) {
  return client({
    url: '/api/tag/list',
    method: 'post',
    data: data
  })
}
