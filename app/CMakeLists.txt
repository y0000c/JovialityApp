#指定CMake的最小版本
cmake_minimum_required(VERSION 3.4.1)

#添加我们自己的模块，名字是native-lib，SHARED可分享的，以及配置源文件或者头文件的路径


add_library(
          native-lib
          SHARED
          src/main/jni/native-lib.c )

find_library( log-lib
              log )

target_link_libraries(
                       native-lib
                       ${log-lib} )

