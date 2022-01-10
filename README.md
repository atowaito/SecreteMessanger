非対称鍵暗号 on Java
===

## 参考資料

[参考資料](https://terasolunaorg.github.io/guideline/5.1.1.RELEASE/ja/Security/Encryption.html#id15)

## 鍵情報を格納しないようにgitを設定しよう

.gitignoreに以下のエントリを追加

```txt: .gitignore
**/resources/keys/**/*

```

## opensslコマンドでKeypairを作成

```
$ openssl genrsa -out src\test\resources\keys\private.pem 2048  # (1)

$ openssl pkcs8 -topk8 -nocrypt -in src\test\resources\keys\private.pem -out src\test\resources\keys\private.pk8 -outform DER  # (2)

$ openssl rsa -pubout -in src\test\resources\keys\private.pem -out src\test\resources\keys\public.der -outform DER  # (3)

```

