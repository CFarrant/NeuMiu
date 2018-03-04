package com.neumiu.io.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.jflac.FLACDecoder;
import org.jflac.FrameListener;
import org.jflac.frame.Frame;
import org.jflac.metadata.Metadata;

public class FlacAnalyzer implements FrameListener {
	
	public double analyse(File file) throws IOException {
		FileInputStream is = new FileInputStream(file);
		FLACDecoder decoder = new FLACDecoder(is);
		decoder.addFrameListener(this);
		decoder.decode();
		double totalSample = decoder.getStreamInfo().getTotalSamples();
		double sampleRate = decoder.getStreamInfo().getSampleRate();
		return totalSample/sampleRate;
	}

	@Override
	public void processError(String arg0) {}

	@Override
	public void processFrame(Frame arg0) {}

	@Override
	public void processMetadata(Metadata arg0) {}
}
