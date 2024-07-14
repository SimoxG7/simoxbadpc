import requests

#headers = {"Host": "0539797708b72d8567d3af69ced73bce"}
#r = requests.get("http://localhost", headers=headers)
#r = requests.get("http://localhost:80/476cff6660bf557d65b5b46e4dc0d869")
#url = "http://localhost:80/babfa178 e1247a42/718fa71b cbc1182d"
#url = url.replace(" ", "%20")
#url = "http://localhost:80/?a=4076f7e9b62b200801e38a108d167308&b=27e1c85b%20ab365344%26ece3f81e%2367847e66"
url = "http://localhost:80/"
#r = requests.get(url)
#r = requests.post(url, data={"a": "17b399c6fcae814809a3fdb37c75d3fa", "b": "ad3c1550 1b726cca&1e00c458#bf73cf3b"})
#r = requests.post(url, headers={"Content-Type":"application/json", "Content-Length":"40"}, data={"a":"72409ff488ee5b2b0ad589f709399623"})
#headers={"Content-Type":"application/json"}
#json = {"a":"c686209a94f74877d6e141e5a758847d", "b":{"c":"ca6cc4ee","d":["4dca0571","0dd08146 dc1643eb&fba6afe3#819d1d10"]}}
#r = requests.post(url, headers=headers, json=json)
r = requests.head(url)
print(r)
print(r.text)
cookie = r.headers.get('Set-Cookie')
r2 = requests.get("http://localhost:80", headers={"Cookie":cookie})
print(r2)
print(r2.text)