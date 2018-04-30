# RSAServer-SpringBoot
[![Build Status](https://travis-ci.org/tigi44/RSAServer-SpringBoot.svg?branch=master)](https://travis-ci.org/tigi44/RSAServer-SpringBoot)

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
