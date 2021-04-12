import {client} from "./request";

export const UploadFile = (file, callback) => {
    const formData = new FormData();
    formData.append('file', file);
    return client.post('utils/upload', formData)
        .then(response => {
            callback(response)
        }).catch(e => {
        }).finally(() => {
        })
};
