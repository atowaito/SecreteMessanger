// ignore: file_names

class ChartData {
  double _data = 0.0;
  String _name = "";

  void setData(double data) {
    this._data = data;
  }

  double getData() {
    return _data;
  }

  void setName(name) {
    this._name = name;
  }

  String getName() {
    return this._name;
  }
}

class ChartManager {
  static bool initialized = false;
  static void init() {
    if (initialized) {
    } else {
      initialized = true;
    }
  }
}
