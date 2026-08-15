# LiveScreen — بدون VPS (مرحلة الاختبار)

البنية:
S9+ -> LiveKit Cloud SFU (مسار فيديو واحد) -> المشاهدون
Firebase لا ينقل الفيديو.

الإعدادات:
LiveKit URL:
wss://live-z32agtvo.livekit.cloud

Development Token Server:
https://live-1corft.sandbox.livekit.io

Firebase project:
hack-fa8fb

## المطلوب منك
1. افتح مجلد `android` في Android Studio.
2. نفّذ Gradle Sync.
3. Build APK.
4. ثبّت التطبيق على S9+.
5. افتح التطبيق واضغط بدء البث.
6. وافق على MediaProjection.

## المشاهدة
استضف مجلد `web` على GitHub Pages أو Firebase Hosting عبر HTTPS.
صفحة المشاهد تتصل بنفس LiveKit Cloud وتطلب token من Development Token Server.

## ملاحظة
Development Token Server للاختبار فقط. للنشر العام نستخدم Token Server خاصًا يحتفظ بـ API Secret على الخادم.

## الأداء
- بدون صوت.
- الهدف 60 FPS.
- 720p كبداية لتقليل التأخير.
- الهاتف يرفع مسار فيديو واحد إلى SFU.
