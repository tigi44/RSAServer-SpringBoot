# RSAServer-SpringBoot
![Java CI with Maven](https://github.com/tigi44/RSAServer-SpringBoot/workflows/Java%20CI%20with%20Maven/badge.svg)

## SERVER
- Spring Boot 2.0.1
- JAVA 8

## CLIENT
- https://github.com/tigi44/RSAClient-iOS

## Test Case
### testRSA
- Request : encryptByPublicKey (param : 'plainText', result : 'encryptText')
- Request : decryptByPrivateKey (param : 'encryptText', result : 'decryptText')
- Confirm : 'decryptText' is equal to 'plainText'
