~~# FlorisBoard 한글 자모 플러그인~~

~~[![Build APK](https://github.com/sivwen/HangulJamoPlugin/actions/workflows/build-apk.yml/badge.svg)](https://github.com/sivwen/HangulJamoPlugin/actions/workflows/build-apk.yml)~~

<del>FlorisBoard 키보드에서 한글을 자모(ㄱ, ㅏ, ㅇ) 단위로 삭제할 수 있는 독립 플러그인입니다.

## ✨ 기능

- **자모 단위 삭제**: 백스페이스로 한글을 한 글자씩이 아닌 자모 단위로 삭제
- **독립 설치**: FlorisBoard와 별도로 설치 및 업데이트 가능
- **안전한 통신**: ContentProvider를 통한 앱 간 통신

## 📥 다운로드

[Releases 페이지](https://github.com/sivwen/HangulJamoPlugin/releases)에서 최신 APK를 다운로드하세요.

## 📱 설치 방법

1. Releases에서 APK 파일 다운로드
2. 안드로이드 기기로 전송
3. "알 수 없는 출처" 허용 후 설치
4. FlorisBoard를 재시작하면 자동으로 플러그인 감지

## 🔨 빌드 방법

```bash
# Debug APK
./gradlew assembleDebug

# Release APK
./gradlew assembleRelease
```

## 🚀 GitHub Actions 자동 빌드

태그를 푸시하면 자동으로 APK가 빌드되어 Releases에 업로드됩니다:

```bash
git tag v1.0.0
git push origin v1.0.0
```

## 📖 작동 원리

### 자모 삭제 예시

- **입력**: "값" (ㄱ + ㅏ + ㅂ + ㅅ)
- **백스페이스 1회**: "갑" (ㅅ 삭제)
- **백스페이스 2회**: "가" (ㅂ 삭제)
- **백스페이스 3회**: "ㄱ" (ㅏ 삭제)
- **백스페이스 4회**: "" (ㄱ 삭제)

### 복합 중성 처리

- **입력**: "과" (ㄱ + ㅗ + ㅏ)
- **백스페이스 1회**: "고" (ㅏ 삭제, ㅗ만 남음)
- **백스페이스 2회**: "ㄱ" (ㅗ 삭제)

## 🏗️ 프로젝트 구조

```
HangulJamoPlugin/
├── .github/workflows/
│   └── build-apk.yml          # GitHub Actions 자동 빌드
├── app/
│   ├── src/main/
│   │   ├── java/com/florisboard/plugin/hangul/
│   │   │   ├── HangulJamoProvider.kt      # ContentProvider
│   │   │   ├── HangulUtils.kt             # 한글 유틸리티
│   │   │   ├── JamoBackspaceHandler.kt    # 자모 삭제 로직
│   │   │   └── PluginSettingsActivity.kt  # 설정 화면
│   │   ├── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

## 🔧 기술 스택

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **IPC**: ContentProvider
- **Build**: Gradle + GitHub Actions
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## 📄 라이선스

Apache License 2.0

## 🤝 기여

이슈 및 PR은 언제나 환영합니다!

## 🔗 관련 링크

- [FlorisBoard GitHub](https://github.com/florisboard/florisboard)
- [한글 유니코드 명세](https://www.unicode.org/charts/PDF/UAC00.pdf)</del>
