class AbstractModel {


  constructor() {

  }

  deserialize (params) {

  }

  /**
   * Serialize object into a JSON string.
   * @return {string}
   */
  toJsonString() {
    return JSON.stringify(this);
  }

  /**
   * Deserialize from a JSON string to an object.
   * @param  {string} dataString
   */
  fromJson(dataString) {
    let params = JSON.parse(dataString);
    this.deserialize(params);
  }
}


module.exports = AbstractModel;
