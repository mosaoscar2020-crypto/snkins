# LiveScreen — GitHub APK Build

هذا المشروع يحتوي على تطبيق Android وصفحة Web للمشاهدة، مع إعدادات LiveKit التي تم تحديدها للمشروع.

## بناء APK عبر GitHub بدون Android Studio

1. ارفع محتويات هذا المجلد إلى مستودع GitHub.
2. تأكد أن الملف التالي موجود:
   `.github/workflows/build-apk.yml`
3. افتح تبويب **Actions**.
4. اختر **Build Android APK**.
5. اضغط **Run workflow**.
6. بعد نجاح البناء افتح العملية ثم قسم **Artifacts**.
7. نزّل `LiveScreen-debug-apk`.
8. فك الضغط وستجد `app-debug.apk`.

## الإعدادات

- LiveKit URL: `wss://live-z32agtvo.livekit.cloud`
- Development Token Server: `https://live-1corft.sandbox.livekit.io`
- Firebase project: `hack-fa8fb`

## تنبيه أمني

لا تضع LiveKit API Secret داخل التطبيق أو GitHub. Development Token Server مخصص للتطوير والاختبار. قبل نشر الخدمة للعامة استخدم Token Server خاصًا يحتفظ بالـAPI Secret على الخادم فقط.

## ملاحظة

هذا الـworkflow يبني المشروع الحالي كما هو. إذا فشل البناء، افتح عملية Actions وانسخ آخر جزء من الخطأ لإصلاحه.
