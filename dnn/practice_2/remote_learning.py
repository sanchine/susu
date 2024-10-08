
import time
import paramiko

# Параметры для подключения
hostname = "192.168.0.9"
port = 22
username = "4733704"
password = "Alena"

# Создание SSH-соединения
client = paramiko.SSHClient()
client.set_missing_host_key_policy(paramiko.AutoAddPolicy())
client.connect(hostname, port=port, username=username, password=password)

# Запуск скрипта на втором компьютере
stdin, stdout, stderr = client.exec_command('python C:\main.py')
stdin.close()
print(stdout.read().decode('windows-1251'))
print(stderr.read().decode('windows-1251'))
# Закрытие соединения
client.close()