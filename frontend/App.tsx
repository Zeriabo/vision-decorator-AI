import React, {useState} from 'react';
import {View, Button, Image, Text, StyleSheet} from 'react-native';
import * as ImagePicker from 'expo-image-picker';

export default function App() {
  const [photo, setPhoto] = useState<string | null>(null);
  const [decoratedImage, setDecoratedImage] = useState<string | null>(null);

  async function pickImage() {
    let result: any = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ImagePicker.MediaTypeOptions.Images,
      base64: true,
    });
    if (!result.canceled) {
      setPhoto(result.uri);
      sendToBackend(result.base64!);
    }
  }

  async function sendToBackend(base64Image: string) {
    // Example: POST to Quarkus API
    const response = await fetch(
      'http://your-quarkus-server/api/decorate-room',
      {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({imageBase64: base64Image, style: 'modern'}),
      },
    );
    const data = await response.json();
    setDecoratedImage(data.decoratedImageUrl);
  }

  return (
    <View style={styles.container}>
      <Button title="Pick a room photo" onPress={pickImage} />
      {photo && <Image source={{uri: photo}} style={styles.image} />}
      {decoratedImage && (
        <>
          <Text>Decorated Room:</Text>
          <Image source={{uri: decoratedImage}} style={styles.image} />
        </>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {flex: 1, justifyContent: 'center', alignItems: 'center'},
  image: {width: 300, height: 200, marginTop: 20},
});
