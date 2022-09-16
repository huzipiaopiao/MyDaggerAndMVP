# MyDaggerAndMVP
个人根据dagger编写的一个MVP小项目，后续继续升级中

个人对MVP的理解不够深入，欢迎提出宝贵意见，共同进步

高版本的gradle，需要修改：
修改app.gradle中的
删除：apply plugin: 'android-apt'
修改：annotationProcessor  'com.google.dagger:dagger-compiler:2.5'
修改：compile 'com.github.tcking:giraffeplayer2:0.1.11' 为  compile 'com.github.tcking:giraffeplayer2:0.1.25'
删除：compile 'com.github.tcking:ijkplayer-x86:0.8.4'
