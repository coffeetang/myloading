import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("maven-publish")
    id("signing")
}

//tasks.register<Jar>("sourcesJar") {
//    archiveClassifier.set("sources")
//    val sources = sourceSets.map { set -> set.java.srcDirs}
//    from(sources)
//}



repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
//            afterEvaluate {
//                publishing {
//                    repositories {
//                        maven {
//                            // isAllowInsecureProtocol = true // 如果Maven仓库仅支持http协议, 请打开此注释
//                            url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/") // 请填入你的仓库地址
//                            authentication {
//                                create<BasicAuthentication>("basic")
//                            }
//                            credentials {
//                                username = "Coffeetang" // 请填入你的用户名
//                                password = "Xiu0616skone#" // 请填入你的密码
//                            }
//                        }
//                        maven {
//                            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/") // 请填入你的仓库地址
//                            authentication {
//                                create<BasicAuthentication>("basic")
//                            }
//                            credentials {
//                                username = "Coffeetang" // 请填入你的用户名
//                                password = "Xiu0616skone#" // 请填入你的密码
//                            }
//                        }
//                    }
//
//                    publications {
//                        create<MavenPublication>("release") {
////                from(components["jvmMain"])
//                            groupId = "io.github.coffeetang" // 请填入你的组件名
//                            artifactId = "CoffeeLoading" // 请填入你的工件名
//                            version = "1.0.0" // 请填入工件的版本名
//                            artifact(tasks["sourcesJar"]) // 打包源码到工件中
//
//                            pom {
//                                name.set("CoffeeLoading") // (可选)为工件取一个名字
//                                url.set("https://github.com/coffeetang/myloading") // (可选)网站地址
//                                packaging = "jar"
//                                developers {
//                                    developer {
//                                        name.set("Coffeetang") // (可选)开发者名称
//                                        email.set("1046569335@qq.com") // (可选)开发者邮箱
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
        }
        val jvmTest by getting
    }
}
group = "io.github.coffeetang" // 请填入你的组件名
version = "1.0.0" // 请填入工件的版本名




compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "myloading"
            packageVersion = "1.0.0"
        }
    }
}
