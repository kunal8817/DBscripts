data "aws_ami" "ami_details" {
  most_recent = true
  owners      = ["amazon"]
}

resource "aws_instance" "Test_EC2" {
  ami           = data.aws_ami.ami_details
  instance_type = "t2.micro"
}