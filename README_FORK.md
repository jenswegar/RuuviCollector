# Fork of RuuviCollector

This fork exists only to provide a pre-built docker image of the source repository. No guarantee is made on my part that the provided image is in a working state, as it is purely based on the source code from the original repository.

The image is built by simply updating the repository with the latest changes from the Scrin/RuuviCollector project and running the docker build command on the provided Dockerfile.

The image is tagged as ```jenswegar/ruuvi-collector:latest``` and also ```jenswegar/ruuvi-collector:{commit-hash}``` where {commit-hash} is the short git commit id that the image was based on.


The image does not compile any ```.properties``` files into it, so you'll need to bind mount your own versions of the files when running the image.