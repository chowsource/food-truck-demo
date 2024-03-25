import { message } from 'antd';
import axios from 'axios';
// import { enhance } from 'foca-axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080',
});

instance.interceptors.response.use(undefined, (err) => {
    message.error(err.message);

    return Promise.reject(err);
});

export const http = enhance(instance, {
    cache: {
        enable: false,
    },
    throttle: {
        enable: true,
    },
    retry: {
        enable: true,
    },
});
