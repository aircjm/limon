#导入nginx镜像
FROM nginx:stable-alpine
#把当前打包工程的html复制到容器目录下
COPY dist/ /usr/share/nginx/html/
#使用自定义nginx.conf配置端口和监听
RUN rm /etc/nginx/conf.d/default.conf
ADD default.conf /etc/nginx/conf.d/
