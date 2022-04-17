import 'package:flutter/cupertino.dart';
import 'package:random_color/random_color.dart';

class FieldData {
  String _target = "";
  double _score = 1.0;
  Color _graphColor = Color.fromARGB(255, 35, 187, 43);
  Color _graphFontColor = Color.fromARGB(256, 256, 256, 256);

  FieldData(String data) {
    _target = data;
    RandomColor _randomColor = RandomColor();
    _graphColor = _randomColor.randomColor(
        colorSaturation: ColorSaturation.lowSaturation);
  }

  double getScore() {
    return _score;
  }

  void setScore(double score) {
    this._score = score;
  }
}

class FieldDataManager {
  static List<FieldData> _lineDatas = [];
  static Map<String, double> _apiData = {};

  static addField(String data) {
    _lineDatas.add(new FieldData(data));
  }

  static setFields(List<FieldData> list) {
    _lineDatas = list;
  }

  static removeLastField() {
    _lineDatas.removeLast();
  }

  static setMap(Map<String, String> map) {
    Map<String, double> resultMap = {};
    for (String item in map.keys) {
      String? val = map[item];
      resultMap[item] = double.parse(val!);
    }
    _apiData = resultMap;
  }

  static Map<String, String> getDataAsMap() {
    Map<String, String> result = {};

    for (var item in _lineDatas) {
      result[item._target] = item._score.toString();
    }
    return result;
  }

  static List<FieldData> getData() {
    if (_lineDatas.length == 0) {
      _lineDatas.add(new FieldData(""));
    }
    return FieldDataManager._lineDatas;
  }
}
