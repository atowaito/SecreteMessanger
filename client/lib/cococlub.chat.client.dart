library cococlub.chat.client;

import 'package:http/http.dart' as http;
//part "src/cococlub.chat.client.interface.dart";
//part "src/cococlub.chat.client.secretetcliet.dart";

class ApiExecuter {
  void getSample() async {
    var url = Uri.parse('https://example.com/whatsit/create');
    var response =
        await http.post(url, body: {'name': 'doodle', 'color': 'blue'});
    print('Response status: ${response.statusCode}');
    print('Response body: ${response.body}');
  }
}
