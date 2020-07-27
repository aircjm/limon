import toml
import os
import oss2
import sys


#本地文件夹路径目录
basedir='dist'
#阿里云存储目录，要保证和下面的project_name相同
projectList = ['limon\']
#本地文件夹
dirList = [basedir]

ossDir=''
basedir=''
#将accessKeyId 和 accessKeySecret替换自己的
#bucketName

auth = oss2.Auth(conf['OSS']['AccessKeyId'], conf['OSS']['AccessKeySecret'])
bucket = oss2.Bucket(auth, conf['OSS']['EndPoint'], conf['OSS']['Bucket'])


conf = toml.loads(open('/etc/conf/oss.toml').read())



def uploadFile2Oss(pro):
	if pro in projectList:
		print('>>>>>>>>>>>>>>>>>Upload:'+ pro +'---Start!')
		global ossDir,basedir
		ossDir, basedir= pro,dirList[projectList.index(pro)]
		#填写自己的阿里云bucket名称
		#http://oss-cn-hangzhou.aliyuncs.com替换为自己的阿里云地址
		listFile(basedir)
	else:
		print('请检查填写的bucket名称和地址是否正确')


def uploadFile(file):
    # remoteName为oss目录名称,file为本地上传目录名称
    remoteName = ossDir + file.replace(basedir,'').replace('\\','/')
    print ('uploading..',file,'remoteName',remoteName)
    result=bucket.put_object_from_file(remoteName,file)
    #文件上传成功http状态输出200
    print('http status: {0}'.format(result.status))

def listFile(dir):
    fs=os.listdir(dir)
    for f in fs:
        file=dir+'\\'+f;

        if os.path.isdir(file):
            listFile(file)
        else:
            uploadFile(file)

if __name__ == '__main__':
    # 填写oss的存储路径
    project_name = 'limon/'
	uploadFile2Oss(project_name)
