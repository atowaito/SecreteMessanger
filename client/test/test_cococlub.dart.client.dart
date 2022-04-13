import 'package:flutter_test/flutter_test.dart';
import 'package:client/cococlub.chat.client.dart';

void main() {
  test(
    "テストサンプル",
    () {
      expect(1, 1);
    },
  );

  test(
    "ApiExecuterのテスト",
    () {
      var apiExecuter = ApiExecuter();
      apiExecuter.getSample();
    },
  );
}
